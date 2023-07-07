// SPDX-License-Identifier: MIT
pragma solidity ^0.6.0;

contract Lottery {
    struct BetInfo {
        // 맞춰야 하는 블록의 넘버
        uint256 answerBlockNumber;
        // 참여자의 주소
        // 특정 주소로 돈을 보내기 위해서는 payable 이라는 키워드가 반드시 필요
        address payable bettor;
        // 맞춰야 할 값 0xab
        bytes1 challenges; 
    }

    mapping (uint256 => BetInfo) private _bets; // 큐
    uint256 private _head;
    uint256 private _tail;

    address public owner;

    uint256 constant internal BLOCK_LIMIT = 256; // 블록 해쉬 제한
    uint256 constant internal BET_BLOCK_INTERVAL = 3; // +3번 블록으로 고정
    uint256 constant internal BET_AMOUNT = 5 * 10 ** 15; // 0.005 ETH 배팅 금액 고정

    uint256 private _pot; // 팟머니


    enum BlockStatus {Checkable, NotRevealed, BlockLimitPassed}
    // 이벤트 생성
    event BET(uint256, address bettor, uint256 amount, bytes1 challenges, uint256 answerBlockNumber);

    constructor() public {
        owner = msg.sender;
    }

    function getSomeValue() public pure returns (uint256 value) {
        return 5;
    }

    // 스마트 컨트랙트에 있는 변수의 값을 조회하기 위해서는 view 키워드가 필요
    function getPot() public view returns (uint256 pot) {
        return _pot;
    }

    // 배팅 함수 - 배팅한 값을 큐에 저장
    /*
     * @dev 배팅 수행, 유저는 0.005ETH룰 보내면서 1byte 배팅 글자를 전달
     * 큐에 저장된 배팅 정보는 이후 distribute 함수에서 해결
     * @param challenges 유저가 배팅하는 글자
     * @return 함수가 잘 수행되었는지 확인하는 bool 값
     */
    // 사용자가 배팅한 값 challenges + ETH를 배팅하기 위한 payable 
    function bet(bytes1 challenges) public payable returns (bool result) {
        // 배팅한 돈이 제대로 왔는지 확인
        require(msg.value == BET_AMOUNT, "Not enough ETH");

        // 큐에 배팅 정보를 삽입
        require(pushBet(challenges), "Fail to add a new Bet Info");
        
        // 이벤트 로그 출력
        emit BET(_tail - 1, msg.sender, msg.value, challenges, block.number + BET_BLOCK_INTERVAL);

        return true;
    }

    // Distribute
    // 배팅에 성공하면 돈을 돌려주고, 그렇지 않으면 배팅금을 pot에 저장
    function distribute() public {
        // [head] 3 4 5 6 7 8 9 10 [tail]
        uint256 cur;
        BetInfo memory b;
        BlockStatus currentBlockStatus;

        for(cur=_head; cur<_tail; cur++) {
            b = _bets[cur];
            currentBlockStatus = getBlockStatus(b.answerBlockNumber);

            // Checkable : block.number > AnswerBlockNumber && block.number < BLOCK_LIMIT + AnswerBlockNumber 
            if(currentBlockStatus == BlockStatus.Checkable) {
                // if win, bettor gets pot
                

                // if fail, bettor's money goes pot

                // if draw, refund bettor's money
            }

            // Not Revealed : 블록이 마이닝되지 않은 상태 : block.number <= AnswerBlockNumber 
            if(currentBlockStatus == BlockStatus.NotRevealed) {
                break;
            }

            // Block Limit Passed : 블록 제한이 지났을 때 : block.number >= AnswerBlockNumber + BLOCK_LIMIT 
            if(currentBlockStatus == BlockStatus.BlockLimitPassed) {
                // refund

                // emit refund event
            }
            
            popBet(cur);

            // check the answer
        }
    }

    function getBlockStatus(uint256 answerBlockNumber) internal view returns (BlockStatus) {
        if(block.number > answerBlockNumber && block.number < BLOCK_LIMIT + answerBlockNumber) {
            return BlockStatus.Checkable;
        }  

        if(block.number <= AnswerBlockNumber){
            return BlockStatus.NotRevealed;
        }

        if(block.number >= AnswerBlockNumber + BLOCK_LIMIT) {
            return BlockStatus.BlockLimitPassed;
        }

        return BlockStatus.BlockLimitPassed;
    }

    // 검증 함수 - 결과 값을 검증
    // _bets에 있는 정보를 가져오는 함수
    function getBetInfo(uint256 index) public view returns (uint256 answerBlockNumber, address bettor, bytes1 challenges) {
        BetInfo memory b = _bets[index];
        answerBlockNumber = b.answerBlockNumber;
        bettor = b.bettor;
        challenges = b.challenges;
    }    

    // _bets 큐에 값을 넣는 함수(PUSH)
    function pushBet(bytes1 challenges) internal returns (bool) {
        BetInfo memory b;
        b.bettor = msg.sender;
        b.answerBlockNumber = block.number + BET_BLOCK_INTERVAL;
        b.challenges = challenges;

        _bets[_tail] = b;
        _tail++;

        return true;
    }

    // _bets 큐에 값을 빼는 함수(POP)
    function popBet(uint256 index) internal returns (bool) {
        // delete를 하면 가스가 환불되기 때문에 필요하지 않은 값에 대해서는 delete를 하는 것이 좋음 
        delete _bets[index];
        return true;
    }

}
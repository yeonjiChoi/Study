// build 폴더 안의 Lottery.json 파일 가져오기
const Lottery = artifacts.require("Lottery");
const assertRevert = require("./assertRevert");
const expectEvent = require("./expectEvent");

contract('Lottery', function([deployer, user1, user2]) {
    let lottery;
    let betAmount = 5 * 10 ** 15;
    let bet_block_interval = 3;

    beforeEach(async () => {
        // lottery라는 변수에 스마트 컨트랙트 배포
        lottery  = await Lottery.new();
    })

    it('getPot should return current pot', async () => {
        let pot = await lottery.getPot();
        assert.equal(pot, 0);
    })

    describe.only('Bet', function () {
        // 배팅을 위한 0.005ETH가 제대로 들어오지 않았을 때 반송
        it('Should fail when the bet money is not 0.005ETH', async () => {
            // Fail transaction - 0.004 ETH를 보냈을 경우

            // assertRevert
            // 트랜잭션이 실패하면 Error 문구를 던짐 "Error: VM Exception while processing transaction: revert Not enough ETH"
            // 이 에러 문구를 assertRevert 에서 받아서 처리
            await assertRevert('0xab', {from: user1, value: 4000000000000000});
            // transaction object => chainId, value, to, from, gas(Limit), gasPrice

        })

        it('Should put the bet to the bet queue with 1 bet', async () => {
            // 1. 배팅하기
           
            // 트랜잭션을 값으로 받을 수 있음 => receipt
            let receipt = await lottery.bet('0xab', {from: user1, value: betAmount});
            
            let pot = await lottery.getPot();
            assert.equal(pot, 0);
            
            // 2. Check contract balance == 0.005 ETH
            // 컨트랙트에 Balance가 제대로 쌓였는지 확인
            // ETH를 스마트컨트랙트에 보내면, 스마트컨트랙트 주소가 ETH를 들고 있어 컨트랙트 주소에 Balance가 생김
            let contractBalance = await web3.eth.getBalance(lottery.address);
            // 컨트랙트 주소에 생긴 balance가 0.005 배팅 금액과 같은지 확인
            assert.equal(contractBalance, betAmount);
            
            // 3. 배팅 정보가 들어왔는지 확인
            let currentBlockNumber = await web3.eth.getBlockNumber();
            
            let bet = await lottery.getBetInfo(0); // 0번 인덱스의 BetInfo를 가져옴
            
            assert.equal(bet.answerBlockNumber, currentBlockNumber + bet_block_interval);
            assert.equal(bet.bettor, user1);
            assert.equal(bet.challenges, '0xab')
            
            // 4. 로그 확인 
            // console.log(receipt);
            await expectEvent.inLogs(receipt.logs, 'BET');
        })
    })
});
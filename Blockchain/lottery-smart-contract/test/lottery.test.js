// build 폴더 안의 Lottery.json 파일 가져오기
const Lottery = artifacts.require("Lottery");

contract('Lottery', function([deployer, user1, user2]) {
    let lottery;

    beforeEach(async () => {
        console.log('Before each');
        
        // lottery라는 변수에 스마트 컨트랙트 배포
        lottery  = await Lottery.new();
    })

    it.only('getPot should return current pot', async () => {
        console.log('Basic test');

        let pot = await lottery.getPot();
        
        console.log(`pot : ${pot}`);
    })
});
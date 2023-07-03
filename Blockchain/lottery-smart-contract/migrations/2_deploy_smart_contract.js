// build 폴더 안의 Lottery.json 파일 가져오기
const Lottery = artifacts.require("Lottery");

module.exports = function(deployer) {
  deployer.deploy(Lottery);
};

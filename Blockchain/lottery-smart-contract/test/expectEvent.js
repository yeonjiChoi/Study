const assert = require('chai').assert;

// logs에서 찾고자하는 eventName이 있는지 확인
const inLogs = async (logs, eventName) => {
    const event = logs.find(e => e.event === eventName);
    assert.exists(event);
}

module.exports = {
    inLogs
}
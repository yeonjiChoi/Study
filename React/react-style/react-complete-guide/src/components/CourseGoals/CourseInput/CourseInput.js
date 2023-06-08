import React, { useState } from "react";

import Button from "../../UI/Button/Button";
import "./CourseInput.css";

const CourseInput = (props) => {
    const [enteredValue, setEnteredValue] = useState("");
    // 사용자가 입력한 값이 유효한지를 나타내는 지표
    const [isValid, setIsValid] = useState(true);

    const goalInputChangeHandler = (event) => {
        // 값이 입력될 경우 -> 스타일 해제
        if (event.target.value.trim().length > 0) {
            setIsValid(true);
        }
        setEnteredValue(event.target.value);
    };

    const formSubmitHandler = (event) => {
        event.preventDefault();
        // 아무 값도 입력되지 않은 경우
        if (enteredValue.trim().length === 0) {
            setIsValid(false);
            return;
        }
        props.onAddGoal(enteredValue);
    };

    return (
        <form onSubmit={formSubmitHandler}>
            <div className={`form-control ${!isValid ? 'invalid' : ''}`}>
                <label>Course Goal</label>
                <input type="text" onChange={goalInputChangeHandler} />
            </div>
            <Button type="submit">Add Goal</Button>
        </form>
    );
};

export default CourseInput;

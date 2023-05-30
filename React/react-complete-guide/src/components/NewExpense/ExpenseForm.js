import "./ExpenseForm.css";
import { useState } from "react";

const ExpenseForm = () => {
    const [enteredTitle, setEnteredTitle] = useState("");
    const [enteredAmount, setEnteredAmount] = useState("");
    const [enteredDate, setEnteredDate] = useState("");

    // const [userInput, setUserInput] = useState({
    //     enteredTitle: "",
    //     enteredAmount: "",
    //     enteredDate: "",
    // });

    const titleChangeHandler = (event) => {
        setEnteredTitle(event.target.value);

        // Bad Code
        // setUserInput({
        //     ...userInput, // Spread 연산자
        //     enteredTitle: event.target.value,
        // });

        // setUserInput 함수에 함수를 전달
        // 전달되는 함수는 리액트에 의해 자동으로 실행됨
        // setUserInput((prevState) => {
        //     // prevState:  이전의 state 스냅샷을 전달
        //     return {
        //         // 새로운 state의 스냅샷을 반환
        //         // 항상 최신 상태의 스냅샷에서 작업하도록 하는 안전한 방법
        //         // 이전 상태에 따라 상태를 업데이트를 할 때마다 해당 함수를 사용
        //         ...prevState,
        //         enteredTitle: event.target.value,
        //     };
        // });
    };

    const amountChangeHandler = (event) => {
        setEnteredAmount(event.target.value);
        // setUserInput({
        //     ...userInput, // Spread 연산자
        //     enteredAmount: event.target.value,
        // });
    };

    const dateChangeHandler = (event) => {
        setEnteredDate(event.target.value);
        // setUserInput({
        //     ...userInput, // Spread 연산자
        //     enteredDate: event.target.value,
        // });
    };

    return (
        <form>
            <div className="new-expense__controls">
                <div className="new-expense__control">
                    <label>Title</label>
                    <input type="text" onChange={titleChangeHandler} />
                </div>
                <div className="new-expense__control">
                    <label>Amount</label>
                    <input
                        type="number"
                        min="0.01"
                        step="0.01"
                        onChange={amountChangeHandler}
                    />
                </div>
                <div className="new-expense__control">
                    <label>Date</label>
                    <input
                        type="date"
                        min="2019-01-01"
                        max="2022-12-31"
                        onChange={dateChangeHandler}
                    />
                </div>
            </div>
            <div className="new-expense__actions">
                <button type="submit">Add Expense</button>
            </div>
        </form>
    );
};

export default ExpenseForm;

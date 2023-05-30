import "./ExpenseItem.css";
import ExpenseDate from "./ExpenseDate";
import Card from "../UI/Card";
import { useState } from "react";

const ExpenseItem = (props) => {

    const [title, setTitle] = useState(props.title); 
    // useState(초기값, 상태의 기본값)
    // useState()는 배열 형태의 값을 반환한다.
    // [현재 상태값, Setter 함수] 

    const clickHander = () => {
        setTitle("Updated!!");
    }

    return (
        <Card className="expense-item">
            <ExpenseDate date={props.date} />
            <div className="expense-item__description">
                <h2>{title}</h2>
                <div className="expense-item__price">${props.amount}</div>
            </div>
            <button onClick={clickHander}>Change Title</button>
        </Card>
    );
};

export default ExpenseItem;

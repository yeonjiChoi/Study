import "./Expenses.css";

import Card from "../UI/Card";
import { useState } from "react";

import ExpenseFilter from "./ExpenseFilter";
import ExpensesList from "./ExpensesList";
import ExpensesChart from "./ExpensesChart";

const Expenses = (props) => {
    const [filteredYear, setFilteredYear] = useState("2021");

    const filterChangeHandler = (selectedYear) => {
        setFilteredYear(selectedYear);
        console.log(selectedYear);
    };

    const filteredExpenses = props.items.filter((expense) => {
        return expense.date.getFullYear().toString() === filteredYear;
    });

    return (
        <div>
            <Card className="expenses">
                <ExpenseFilter
                    onChangeFilter={filterChangeHandler}
                    selected={filteredYear}
                />
                <ExpensesChart expenses={filteredExpenses}/>
                <ExpensesList items={filteredExpenses}/>
            </Card>
        </div>
    );
};

export default Expenses;

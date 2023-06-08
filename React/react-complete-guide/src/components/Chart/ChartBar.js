import "./ChartBar.css";

const ChartBar = (props) => {
    let barFillHeight = '0%';

    if(props.maxValue > 0) {
        // 1 ~ 100까지의 숫자
        barFillHeight = Math.round((props.value / props.maxValue) * 100) + '%';
    }
    
    return (
        <div className="chart-bar">
            <div className="chart-bar__inner">
                <div className="chart-bar__fill" style={{height : barFillHeight}}> </div>
            </div>
            <div className="chart-bar__label">{props.label}</div>
        </div>
    );
};

export default ChartBar;

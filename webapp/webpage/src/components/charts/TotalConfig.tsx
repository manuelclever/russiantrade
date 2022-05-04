import React from "react";
import { ChartConfiguration } from 'chart.js';
import { Chart } from 'react-chartjs-2';

class TotalConfig extends React.Component {
    constructor(props: any) {
        super(props);
        this.state = {
            type: "bar",
            data: null
        }
    }

    render() {

        return (
            <Chart id="exportOverview" type={this.state.type} data={this.state.data}/>
        );
    }


}
export default TotalConfig;
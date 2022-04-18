import { ChartConfiguration, ChartType, ChartData, ChartOptions } from 'chart.js';
import 'chart.js/auto';
import {Doughnut, BarChart} from './components/Chart';
import Test from "./components/test";

import './App.css';

export interface myCustomChartConfig {
  type: ChartType,
  data: ChartData,
  options?: ChartOptions,
  width?: number,
  height?: number
}

function App(this: any) {

  var config: ChartConfiguration = {
    type: "bar",
    data: {
      labels: [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June'],
      datasets: [
          {
              label: 'My First dataset',
              backgroundColor: ['rgb(205,92,92)'],
              borderColor: 'rgb(255, 99, 132)',
              data: [2, 10, 5, 2, 20, 30, 45] 
          }
      ]
    },
    options: {},
  }

  return (
    <div>
      <Test/>
      <Doughnut type={config.type} data={config.data}/>
    </div>
  );
}

export default App;

import {ChartConfiguration} from 'chart.js/auto';
import { myCustomChartConfig } from '../App';
import {Chart} from 'react-chartjs-2';

export function Doughnut(config: ChartConfiguration) {
    return (
        <div>
            <Chart
                type={config.type}
                data={config.data}
            />
        </div> 
        );
}

export const BarChart: () => JSX.Element = () => {
    return (
      <div>
        <Chart
            type='bar'
            datasetIdKey='firstChart'
            data={{
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
                        backgroundColor: 'rgb(205,92,92)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: [2, 10, 5, 2, 20, 30, 45] 
                    },
                    {
                        label: 'My Second dataset',
                        backgroundColor: 'rgb(124, 99, 132)',
                        borderColor: 'rgb(124, 99, 132)',
                        data: [4, 20, 10, 4, 40, 60, 90] 
                    }
                ]
            }} 
            height={200} 
            width={600}/>
      </div>
    );
}
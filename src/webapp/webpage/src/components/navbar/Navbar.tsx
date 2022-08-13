import Select from "react-select";
import React from "react";
import { ChartConfiguration, ChartData } from 'chart.js';
import { SingleValue } from "react-select";

import dropdownOptions from './Dropdown';
import { Option } from './DropdownData';
import './navbar.css';
import { optionCSS } from "react-select/dist/declarations/src/components/Option";

interface Data {
    selected: Option,
    exportBarChart: ChartData;
    importBarChart: ChartData;
}

class Navbar extends React.Component<any, Data> {
    constructor(props: any) {
        super(props);
        this.state = {
            selected: dropdownOptions[0].options[0],
            exportBarChart: {
                labels: this.labels,
                datasets: [
                    {
                        label: 'No Data',
                        backgroundColor: ['#6d597a', '#b56576', '#e56b6f', '#eaac8b'],
                        borderColor: '#355070',
                        data: [0] 
                    }
                ]
            },
            importBarChart: {
                labels: this.labels,
                datasets: [
                    {
                        label: 'No Data',
                        backgroundColor: ['#6d597a', '#b56576', '#e56b6f', '#eaac8b'],
                        borderColor: '#355070',
                        data: [0] 
                    }
                ]
            }
        }
    }

    labels: string[] = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
    ];

    refresh(el: SingleValue<Option>) {
        var dataExportBarChart: any = fetch("localhost:8081/api/requestTotal?country=" + el?.value?.toString().split("_")[1] + "&trade_flow=Export&periodStart=2010&periodEnd=2020")
            .then(response => response.json());
        var dataImportBarChart: any = fetch("localhost:8081/api/requestTotal?country=" + el?.value?.toString().split("_")[1] + "&trade_flow=Import&periodStart=2010&periodEnd=2020")
            .then(response => response.json());

        console.log("fetching: localhost:8081/api/requestTotal?country=" + el?.value?.toString().split("_")[1] + "&trade_flow=Export&periodStart=2010&periodEnd=2020")
        console.log(JSON.stringify(dataExportBarChart));

        console.log("fetching: localhost:8081/api/requestTotal?country=" + el?.value?.toString().split("_")[1] + "&trade_flow=Import&periodStart=2010&periodEnd=2020")
        console.log(JSON.stringify(dataImportBarChart));

        this.setState({
            exportBarChart: dataExportBarChart,
            importBarChart: dataImportBarChart
        });
    }

    render() {
        return (
            <div className="containerNavbar fixed-top">      
                    <svg id="nav_logo">
                        <rect x="30" y="-20" width="20" height ="130" transform="rotate(45,30,0)" style={{fill: '#0057B8'}}/>
                        <rect x="58" y="-20" width="20" height ="180" transform="rotate(45,58,0)" style={{fill: '#FFD700'}}/>
                    </svg>
                    <nav className="navbar justify-content-end">
                        <ul className="nav">
                            <Select id="selector" options={dropdownOptions} defaultValue={this.state.selected} onChange={(event) => this.refresh(event)}/>
                            {/* onChange={(event) => this.refresh(event)} */}
                        </ul>
                    </nav>
            </div>
        );
    }
}

export default Navbar;

// export default function navbar() {
//     const style = {
//         flag: {
//             width: '100%',
//             height: '100%',
//         },
//         brand: {
//             marginLeft: '1rem'
//         }
//     }

//     return (
//         <div className="containerNavbar fixed-top">      
//                 <svg id="nav_logo">
//                     <rect x="30" y="-20" width="20" height ="130" transform="rotate(45,30,0)" style={{fill: '#0057B8'}}/>
//                     <rect x="58" y="-20" width="20" height ="180" transform="rotate(45,58,0)" style={{fill: '#FFD700'}}/>
//                 </svg>
//                 <nav className="navbar justify-content-end">
//                     <ul className="nav">
//                         <Select id="selector" options={dropdownOptions} onChange={refresh}/>
//                     </ul>
//                 </nav>
//         </div>
//     );
// };
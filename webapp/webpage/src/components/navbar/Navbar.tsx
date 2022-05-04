import Select from "react-select";
import dropdownOptions from './Dropdown';
import React from "react";
import { ChartConfiguration, ChartData, } from 'chart.js';

import './navbar.css';

interface Data {
    exportBarChart: ChartData;
    importBarChart: ChartData;
}

class Navbar extends React.Component<Data> {
    constructor(props: any) {
        super(props);
        this.state = {
            exportBarChart: 'no Data',
            importBarChart: 'no Data'
        }
    }

    refresh(el: String) {
        var dataExportBarChart = fetch("localhost:8081/api/requestTotal?country=" + el + "&trade_flow=Export&periodStart=2010&periodEnd=2020")
            .then(response => response.json());
        var dataImportBarChart = fetch("localhost:8081/api/requestTotal?country=" + el + "&trade_flow=Import&periodStart=2010&periodEnd=2020")
            .then(response => response.json());

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
                            <Select id="selector" options={dropdownOptions} value={dropdownOptions.find(obj => obj.value === selectedValue)} onChange={this.refresh}/>
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
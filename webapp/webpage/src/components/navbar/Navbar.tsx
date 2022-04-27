import Select from "react-select";
import dropdownOptions from './Dropdown';
import ua_flag from './../../resources/images/svg_flags/ukraine.svg';

import './navbar.css';

export default function navbar() {
    const style = {
        flag: {
            width: '100%',
            height: '100%',
        },
        brand: {
            marginLeft: '1rem'
        }
}

    return (
        <div className="containerNavbar fixed-top">      
                <svg id="nav_logo">
                    <rect x="30" y="-20" width="20" height ="130" transform="rotate(45,30,0)" style={{fill: '#0057B8'}}/>
                    <rect x="58" y="-20" width="20" height ="180" transform="rotate(45,58,0)" style={{fill: '#FFD700'}}/>
                </svg>
                <nav className="navbar justify-content-end">
                    <ul className="nav">
                        <Select id="selector" options={dropdownOptions}/>
                    </ul>
                </nav>
        </div>
    );
};
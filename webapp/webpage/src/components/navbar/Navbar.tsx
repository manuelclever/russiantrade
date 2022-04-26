import Select from "react-select";
import dropdownOptions from './Dropdown';
import ua_flag from './../../resources/images/svg_flags/ukraine.svg';

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
                <img id="navIMG" src={ua_flag} alt='ukraine_flag' style={style.flag}/>
                <nav className="navbar">
                    <a className="navbar-brand" href="localhost:3000" style={style.brand}>Russian Trade</a>
                    <ul className="nav justify-content-start">
                      <Select id="selector" options={dropdownOptions}/>
                    </ul>
                </nav>
        </div>
    );
};
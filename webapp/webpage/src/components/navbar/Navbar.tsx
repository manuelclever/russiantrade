import React from "react";
import Select from "react-select";
import dropwdownOptions from './data';
import ua_flag from "./../../resources/images/flag_ukraine.png";
import { Collection } from "typescript";

export default function navbar() {
    

    


    return (
        <div className="containerNavbar fixed-top">      
                <img id="navIMG" src={ua_flag} alt='flag_ukraine'/>
                <nav className="navbar">
                    <a className="navbar-brand" href="localhost:3000">trade</a>
                    <ul className="nav justify-content-start">
                      <Select id="selector" options={dropwdownOptions}/>
                    </ul>
                </nav>
        </div>
    );
};
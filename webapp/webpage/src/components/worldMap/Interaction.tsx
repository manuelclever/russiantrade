import React from "react";

export default class Interaction extends React.Component {
    constructor(props: any) {
        super(props);
        this.state= {
            fill: '#e0e0e0'
        }
    }

    fill() {
        this.setState({
            fill: '#005bbb'
        })
    }

    unfill() {
        this.setState({
            fill: '#e0e0e0'
        })
    }
}
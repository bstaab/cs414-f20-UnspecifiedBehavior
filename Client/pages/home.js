import React, {Component} from 'react';

export default class Home extends Component {
    constructor(props) {
        super(props);

        this.renderNavbar.bind(this);
    }

    render() {
        return (
            <div>
                {this.renderNavbar()}
            </div>
        )
    }

    renderNavbar() {
        return (
            <div>

            </div>
        )
    }
}
import React, {useState} from 'react'
import { NavLink, withRouter} from "react-router-dom";
//import "./css/Navbar.css"


const Navbar = () => {
    const [isOpen] = useState(false);
    return (
        <nav
            className="navbar navbar-expand-sm justify-content-center fixed-top"
        >
            <div className="container-fluid">
                <div className={` navbar-nav ${isOpen && "is-active"}`}>
                    <NavLink
                        className="navbar-brand"
                        activeClassName="is-active"
                        to="/"
                        exact
                    >
                        Home
                    </NavLink>
                    <div className='nav-item'>
                        <NavLink
                            className="nav-link"
                            activeClassName="is-active"
                            to="/about"
                        >
                            Login
                        </NavLink>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default withRouter(Navbar);


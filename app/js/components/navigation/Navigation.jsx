import React from 'react';
import NavLink from './NavLink.jsx';
import { RouteTransition } from 'react-router-transition';

// Material UI
import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import Divider from 'material-ui/Divider';

// Icons
import IconHome from 'material-ui/svg-icons/action/home';
import IconPowerOff from 'material-ui/svg-icons/action/power-settings-new';

export default class Navigation extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            drawerOpen: false
        };
    }
    
    handleDrawerClose() {
        this.setState({drawerOpen: false});
    };

    handleDrawerToggle() {
        this.setState({drawerOpen: !this.drawerOpen});
    };

    componentDidMount() {
        TweenMax.to('.overlay', 3, {
            top: '-100%',
            delay: 0.4,
            ease: Expo.easeOut, y: 0
        });
    }

    render() {

        const drawerWidth = 300;
        
        const style = {
            drawerImg: {
                width: drawerWidth + 'px',
                height: 'auto'
            },
            lastLink: {
                position: 'absolute',
                width: '100%',
                bottom: '15px'
            }
        };
        
        return(
            <div>
                
                <div className="overlay"></div>
                
                <AppBar
                    title="App name"
                    className="mainAppBar"
                    iconClassNameRight="muidocs-icon-navigation-expand-more"
                    onLeftIconButtonTouchTap={this.handleDrawerToggle.bind(this)}
                />

                <Drawer
                    docked={false}
                    width={drawerWidth}
                    open={this.state.drawerOpen}
                    onRequestChange={(drawerOpen) => this.setState({drawerOpen})}
                >
                    
                    <img className="drawer-image" src="static/imgs/drawer.jpg" style={style.drawerImg} alt="Drawer"/>

                    <NavLink to="/app">
                        <MenuItem onTouchTap={this.handleDrawerClose.bind(this)} leftIcon={<IconHome/>}>
                            Home
                        </MenuItem>
                    </NavLink>

                    <NavLink to="/app/about">
                        <MenuItem onTouchTap={this.handleDrawerClose.bind(this)} leftIcon={<IconHome/>}>
                            About
                        </MenuItem>
                    </NavLink>

                    <Divider/>
                    <NavLink to="/app/about">
                        <MenuItem onTouchTap={this.handleDrawerClose.bind(this)} leftIcon={<IconHome/>}>
                            Contact
                        </MenuItem>
                    </NavLink>

                    <NavLink to="/" style={style.lastLink}>
                        <MenuItem onTouchTap={this.handleDrawerClose.bind(this)} leftIcon={<IconPowerOff/>}>
                            Log out
                        </MenuItem>
                    </NavLink>

                </Drawer>

                <RouteTransition
                    pathname={this.props.location.pathname}
                    atEnter={{ opacity: 0 }}
                    atLeave={{ opacity: 1 }}
                    atActive={{ opacity: 1 }}
                >
                    {this.props.children}
                </RouteTransition>

            </div>
        );
    }
}
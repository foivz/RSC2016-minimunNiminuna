// React
import ReactDOM from 'react-dom';
import React 	from 'react';

// GSAP
import 'gsap';

// Material UI
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
var injectTapEventPlugin = require("react-tap-event-plugin");
injectTapEventPlugin();

// Routes
import Routes from './Routes.jsx';

ReactDOM.render(
    <MuiThemeProvider>
        <Routes />
    </MuiThemeProvider>
    ,document.getElementById('app'));
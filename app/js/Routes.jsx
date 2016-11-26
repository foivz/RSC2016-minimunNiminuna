import React from 'react';

// React Router
import {
    Router,
    Route,
    hashHistory,
    IndexRoute
} from 'react-router';


import Landing from './pages/Landing.jsx';

// Navigation
import Navigation from './components/navigation/NavigationModerator.jsx';

// Components
import Dashboard from './components/moderator/Dashboard.jsx';
import AddModerator from './components/moderator/New-moderator.jsx';
import AddQuiz from './components/moderator/New-quiz.jsx';

export default class Routes extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <Router history={hashHistory}>
                <Route path="/" component={Landing} />
                <Route path="/moderator" component={Navigation}>
                    <IndexRoute component={Dashboard}/>
                    <Route path="/moderator/new-moderator" component={AddModerator}/>
                    <Route path="/moderator/new-quiz" component={AddQuiz}/>
                </Route>
            </Router>
        );
    }
};
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
import NavigationModerator from './components/moderator/NavigationModerator.jsx';
import NavigationUser from './components/user/NavigationUser.jsx';

// Components
import DashboardModerator from './components/moderator/Dashboard.jsx';
import DashboardUser from './components/user/Dashboard.jsx';
import Profile from './components/user/Profile.jsx';
import AddModerator from './components/moderator/New-moderator.jsx';
import AddQuiz from './components/moderator/New-quiz.jsx';
import Quiz from './components/user/Quiz.jsx';
import Team from './components/user/Team.jsx';
import TeamJoin from './components/user/Team-join.jsx';

export default class Routes extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <Router history={hashHistory}>
                <Route path="/" component={Landing} />
                <Route path="/moderator" component={NavigationModerator}>
                    <IndexRoute component={DashboardModerator}/>
                    <Route path="/moderator/new-moderator" component={AddModerator}/>
                    <Route path="/moderator/new-quiz" component={AddQuiz}/>
                </Route>
                <Route path="/user" component={NavigationUser}>
                    <IndexRoute component={DashboardUser}/>
                    <Route path="/user/profile" component={Profile}/>
                    <Route path="/quiz/:id" component={Quiz}/>
                    <Route path="/user/team" component={Team}/>
                    <Route path="/user/team-join" component={TeamJoin}/>
                </Route>
            </Router>
        );
    }
};
import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';

export default class Team extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return(
            <div className="container team-container">
                <div className="row">
                    <div className="col-md-6 col-md-offset-3">
                        <TextField
                            hintText="Team Name"
                            floatingLabelText="Team Name"
                            type="text"
                            fullWidth={true}
                        /><br />
                        <TextField
                            hintText="Description"
                            floatingLabelText="Description"
                            type="text"
                            fullWidth={true}
                        /><br />
                        <RaisedButton
                            label="Add team"
                            primary={true}
                            fullWidth={true}
                        />
                    </div>
                </div>
            </div>
        );
    }
};

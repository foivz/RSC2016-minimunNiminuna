import React from 'react'

// Material UI
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import DatePicker from 'material-ui/DatePicker';
import TimePicker from 'material-ui/TimePicker';

import {
    Step,
    Stepper,
    StepLabel,
} from 'material-ui/Stepper';
import ExpandTransition from 'material-ui/internal/ExpandTransition';


export default class AddQuiz extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selectBoxValue: 1,
            loadingStepper: false,
            finishedStepper: false,
            stepIndex: 0,
        };
        this.handleNext = this.handleNext.bind(this);
        this.handlePrev = this.handlePrev.bind(this);
    }

    dummyAsync (cb) {
        this.setState({loading: true}, () => {
            setTimeout(cb, 500);
        });
    };

    handleNext () {
        const {stepIndex} = this.state;
        if (!this.state.loading) {
            this.dummyAsync(() => this.setState({
                loading: false,
                stepIndex: stepIndex + 1,
                finished: stepIndex >= 2,
            }));
        }
    };

    handlePrev () {
        const {stepIndex} = this.state;
        if (!this.state.loading) {
            this.dummyAsync(() => this.setState({
                loading: false,
                stepIndex: stepIndex - 1,
            }));
        }
    };

    getStepContent(stepIndex) {
        switch (stepIndex) {
            case 0:
                return (
                    <div>
                        <div className="container">
                            <p>Quiz description</p>
                            <TextField
                                floatingLabelText="Name"
                                hintText="Name"
                                fullWidth={true}
                            /><br />
                            <SelectField
                                floatingLabelText="Category"
                                value={this.state.selectBoxValue}
                                onChange={this.handleChange}
                                fullWidth={true}
                            >
                                <MenuItem value={1} primaryText="Informatic" />
                                <MenuItem value={2} primaryText="Mathematic" />
                                <MenuItem value={3} primaryText="Biology" />
                            </SelectField>
                            <br />
                            <br />
                            <DatePicker
                                hintText="Date begin"
                                container="inline"
                                mode="landscape"
                                fullWidth={true}
                            />
                            <TimePicker
                                hintText="Time begin"
                                autoOk={true}
                                fullWidth={true}
                            />
                        </div>
                    </div>
                );
            case 1:
                return (
                    <div>
                        <p>
                            Ad group status is different than the statuses for campaigns, ads, and keywords, though the
                            statuses can affect each other. Ad groups are contained within a campaign, and each campaign can
                            have one or more ad groups. Within each ad group are ads, keywords, and bids.
                        </p>
                        <p>Something something whatever cool</p>
                    </div>
                );
            case 2:
                return (
                    <p>
                        Try out different ad text to see what brings in the most customers, and learn how to
                        enhance your ads using features like ad extensions. If you run into any problems with your
                        ads, find out how to tell if they're running and how to resolve approval issues.
                    </p>
                );
            default:
                return 'You\'re a long way from home sonny jim!';
        }
    }

    renderContent() {
        const {finished, stepIndex} = this.state;
        const contentStyle = {margin: '0 16px', overflow: 'hidden'};

        if (finished) {
            alert("Finished");
        }

        return (
            <div style={contentStyle}>
                <div>{this.getStepContent(stepIndex)}</div>
                <div style={{marginTop: 24, marginBottom: 12}}>
                    <FlatButton
                        label="Back"
                        disabled={stepIndex === 0}
                        onTouchTap={this.handlePrev}
                        style={{marginRight: 12}}
                    />
                    <RaisedButton
                        label={stepIndex === 2 ? 'Finish' : 'Next'}
                        primary={true}
                        onTouchTap={this.handleNext}
                    />
                </div>
            </div>
        );
    }

    render() {
        const {loading, stepIndex} = this.state;

        return (
            <div style={{width: '100%', maxWidth: 700, margin: 'auto'}}>
                <Stepper activeStep={stepIndex}>
                    <Step>
                        <StepLabel>General Quiz informations</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Quiz questions</StepLabel>
                    </Step>
                    <Step>
                        <StepLabel>Awards</StepLabel>
                    </Step>
                </Stepper>
                <ExpandTransition loading={loading} open={true}>
                    {this.renderContent()}
                </ExpandTransition>
            </div>
        );
    }
}
import React from 'react';
import {fetchQuizByID} from '../../utils/appService';
import {RadioButton, RadioButtonGroup} from 'material-ui/RadioButton';
import AudioRecorder from 'react-audio-recorder';

export default class Quiz extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true
        };
    }

    componentWillMount() {
        var quizID = this.props.params.id;
        fetchQuizByID(quizID, (res) => {
            this.setState({
                loading: false,
                quiz: res,
            });
        });
    }

    renderQuestion(index) {
        var questions = this.state.quiz.questions;
        console.log(questions);
        switch(questions[0].cat.idCat) {
            case 1:
                console.log("textbox");
                break;
            case 2:
                console.log("checkbox");
                this.renderCheckbox();
                break;
        }
    }

    renderCheckbox(answers) {
       return(
           <RadioButton
               value="light"
               label="Simple"
           />
       );
    }

    audioChange(data) {
        console.log(data);
    }

    render() {
        if(!this.state.loading)
            return(
                <div className="container">
                    {this.renderQuestion(0)}
                    <AudioRecorder onChange={this.audioChange} />
                </div>
            );

        return(<div></div>);
    }
};
import React from 'react';
import {fetchQuizByID, sendAudio} from '../../utils/appService';

import RaisedButton from 'material-ui/RaisedButton';
import {RadioButton, RadioButtonGroup} from 'material-ui/RadioButton';
import AudioRecorder from 'react-audio-recorder';
import IconArrow from 'material-ui/svg-icons/navigation/chevron-right';


export default class Quiz extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            currentQuestionIndex: 0,
            checkboxAnswer: 0,
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
        switch(questions[index].cat.idCat) {
            case 1: // Textbox
                // return this.renderTextbox();
                break;
            case 2: // Checkbox
                return this.renderCheckbox(questions[index].allAnswers);
                break;
        }
    }

    renderCheckbox(answers) {
        answers = this.parseAnswers(answers);
        return(
           <div>
               <RadioButtonGroup name="checkbox" onChange={this.checkAnswer.bind(this)}>
                   {this.renderCheckboxAnswers(answers)}
               </RadioButtonGroup>
           </div>
        );
    }

    parseAnswers(answers) {
        return answers.split(';');
    }

    renderCheckboxAnswers(answers) {
        return answers.map(function(elem, key) {
            return(
                <RadioButton
                    key={key}
                    value={elem}
                    label={elem}
                />
            );
        }, this);
    }

    checkAnswer(event, value) {
        this.setState({
            checkboxAnswer: value
        });
    }

    nextAnswer() {
        this.setState({
            currentQuestionIndex: this.state.currentQuestionIndex + 1
        });
    }

    audioChange(data) {
        // sendAudio(data.blob, (res) => {
        //     console.log("uspio");
        // });
    }

    render() {
        if(!this.state.loading)
            return(
                <div className="container">
                    {this.renderQuestion(this.state.currentQuestionIndex)}
                    <AudioRecorder onChange={this.audioChange} />
                    <RaisedButton
                        label="Confirm Answer"
                        primary={true}
                        icon={<IconArrow/>}
                        onTouchTap={this.nextAnswer.bind(this)}
                    />
                </div>
            );

        return(<div></div>);
    }
};
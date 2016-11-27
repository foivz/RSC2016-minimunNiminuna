import React from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {fetchQuizes} from '../../utils/appService';
import Chip from 'material-ui/Chip';

export default class DashboardModerator extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true
        };
    }

    componentDidMount() {
        var self = this;
        fetchQuizes((res) => {
            this.setState({
                quizes: res,
                loading: false
            }, function() {
                self.initMap(self.state.quizes);
            });
        });
    }

    initMap(quizes) {
        var position = {lat: 46.308483, lng: 16.340031};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 17,
            center: position
        });
        for(var key in quizes) {
            new google.maps.Marker({
                position: quizes[key].location,
                map: map
            })
        }
    }


    enterQuiz(id) {
        hashHistory.push('/quiz/' + id);
    }

    renderQuizes() {
        return this.state.quizes.map(function(elem, key) {
            var author = "Author - " + elem.creator.credentials.username;
            return(
                <div className="col-md-4" key={key}>
                    <Card style={{marginTop: '10px', marginBottom: '10px'}}>
                        <CardHeader
                            title={author}
                            subtitle={elem.name}
                            avatar="https://www.drupal.org/files/project-images/quiz_317_1081_q_image_large_0.jpg"
                        />
                        <CardMedia
                            overlay={<CardTitle title={elem.name} subtitle="For sixth grade" />}
                        >
                            <img src={elem.category.icon} />
                        </CardMedia>
                        <CardTitle title="QUIZ" subtitle={elem.beginDate + ' - ' + elem.endDate} />
                        <CardText>
                            How much do you know about the countries of the world? Find out with this 10 question quiz.
                        </CardText>
                        <CardActions>
                            <FlatButton label="Statistics" />
                            <FlatButton label="Edit Quiz" onClick={() => this.enterQuiz(elem.idQuiz)} />
                        </CardActions>
                    </Card>
                </div>
            );
        }, this);
    }

    render() {

        const style = {
            chip: {
                backgroundColor: '#c1c1c1',
                marginBottom: '10px',
                marginRight: '10px',
                display: 'inline-block'
            }
        };

        if(!this.state.loading) {
            return(
                <div className="container dashboard-container">
                    <Chip style={style.chip}>
                        #history
                    </Chip>
                    <Chip style={style.chip}>
                        #informatic
                    </Chip>
                    <Chip style={style.chip}>
                        #mathematic
                    </Chip>
                    <div className="row">
                        {this.renderQuizes()}
                    </div>
                    <div id="map"></div>
                </div>
            );
        }

        return(<div></div>);

    }
};
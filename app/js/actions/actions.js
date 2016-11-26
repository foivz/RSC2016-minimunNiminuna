import AppDispatcher from '../dispatcher/dispatcher.js';
import constants     from '../constants/constants.js';

export function someActionFunction(filter) {
	AppDispatcher.handleViewAction({
        type: constants.CONSTANT,
        // OTHER DATA...
	});
}

import AppDispatcher  from '../dispatcher/dispatcher';
import constants      from '../constants/constants';
import {EventEmitter} from 'events';

const CHANGE_EVENT = 'change';

class StoreClass extends EventEmitter {

    emitChange(event) {
        this.emit(event || CHANGE_EVENT);
    }

    addChangeListener(event, callback) {
        this.on(event || CHANGE_EVENT, callback);
    }

    removeChangeListener(event, callback) {
        this.removeListener(event || CHANGE_EVENT, callback);
    }
}

const Store = new StoreClass();

AppDispatcher.register((payload) => {
    const action = payload.action;
    switch(action.type) {
        
        case constants.CONSTANT:
            Store.emitChange('SomeChange');
            break;

        default:
            return true;
    }
});

export default Store;
import React from 'react';
import { shallow } from 'enzyme'
import ChatComponent from '../components/chat/ChatComponent';


it('Chat SNAPSHOT', () => {
    const component = shallow(<ChatComponent />);
  
    expect(component).toMatchSnapshot();
  });

import React from 'react';
import { shallow, mount } from 'enzyme'
import ChatComponent from '../components/chat/ChatComponent';


it('Chat SNAPSHOT', () => {
    const component = shallow(<ChatComponent />);
  
    expect(component).toMatchSnapshot();
  });
it('Send chat message', () => {
    //const component = mount(<ChatComponent />);
    // component
    //   .find('button#my-button-one')
    //   .simulate('keydown', { keyCode: 32 });

    //   const input = component.find('input');

    // expect(component).toMatchSnapshot();
    // component.unmount();
  });

import React from 'react';
import { shallow } from 'enzyme';
import MessageComponent from '../components/chat/MessageComponent.jsx';

  it('MessageComponent SNAPSHOTt', () => {
    const component = shallow(<MessageComponent message="Test message" datetime="28/09/2019 14:57:20" sender="User" />);
  
    expect(component).toMatchSnapshot();
  });
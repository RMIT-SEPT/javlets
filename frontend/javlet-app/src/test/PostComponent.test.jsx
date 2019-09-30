import React from 'react';
import { shallow } from 'enzyme';
import PostComponent from '../components/wall/PostComponent.jsx';

  it('PostComponent SNAPSHOT', () => {
    const component = shallow(<PostComponent/>);
  
    expect(component).toMatchSnapshot();
  });
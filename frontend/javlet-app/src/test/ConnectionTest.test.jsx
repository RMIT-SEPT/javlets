import React from 'react';
import { shallow } from 'enzyme';
import ConnectComponent from '../components/chat/ConnectComponent.jsx';

  it('ConnectComponent SNAPSHOTt', () => {

    // Test type 0
    let component = shallow(<ConnectComponent key={Math.random()} name="John" conID="s1111111" type={0}/>);

    // Test type 1
    component += shallow(<ConnectComponent key={Math.random()} name="Jill" conID="s1111111" type={1}/>);

    // Test type 2
    component += shallow(<ConnectComponent key={Math.random()} name="Max" conID="s1111111" type={2}/>);

    // Test type 3
    component += shallow(<ConnectComponent key={Math.random()} name="Bob" conID="s1111111" type={3}/>);

    // Test type 4
    component += shallow(<ConnectComponent key={Math.random()} name="Soe" conID="s1111111" type={4}/>);
    
    
    expect(component).toMatchSnapshot();
  });
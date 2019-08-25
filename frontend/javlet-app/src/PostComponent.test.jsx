import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import PostComponent from './components/PostComponent';

describe('WallPosts', () => {
    it('returns data when sendMessage is called', done => {
        var mock = new MockAdapter(axios);
        const data = { response: true };
        mock.onGet('http://localhost:8080/wall').reply(200, data);

        PostComponent.refreshWall.then(response => {
            expect(response).toEqual(data);
            done();
        });
    });
});
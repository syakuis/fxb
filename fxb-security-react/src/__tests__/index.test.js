import React from 'react';
import renderer from 'react-test-renderer';

import Index from '../index';

test('good', () => {
  const RootJSON = Index.toJSON();
  console.log(RootJSON);
});

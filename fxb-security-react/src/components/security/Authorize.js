import PropTypes from 'prop-types';
import { connect } from 'react-redux';

const propTypes = {
  children: PropTypes.node.isRequired,
  not: PropTypes.bool,
  user: PropTypes.shape({
    username: PropTypes.string,
    isAllowed: PropTypes.bool,
    isAnonymous: PropTypes.bool,
  }),
};

const defaultProps = {
  children: '',
  not: false,
  user: {},
};

const isAuthorize = user => user.username !== null;

const Authorize = props => (!isAuthorize(props.user) === props.not ? props.children : null);

Authorize.propTypes = propTypes;
Authorize.defaultProps = defaultProps;

const mapStateToProps = state => ({
  user: state.user,
});

export default connect(mapStateToProps, undefined)(Authorize);

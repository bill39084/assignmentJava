import React, { Component } from 'react'
import Scroll from 'react-scroll'

export default class BackToTopButton extends Component {
    state = {
      atTopOfPage: true
    }

  componentDidMount () {
    window.addEventListener('scroll', this.handleScroll)
    this.handleScroll()
  }

  componentWillUnmount () {
    window.removeEventListener('scroll', this.handleScroll)
  }  //ทำลาย Component

  handleScroll = () => {
    if (window.scrollY > 0) {
      if (this.state.atTopOfPage) this.setState({ atTopOfPage: false })
    } else {
      if (!this.state.atTopOfPage) this.setState({ atTopOfPage: true })
    }
  }

  scrollToTop = () => {
    Scroll.animateScroll.scrollToTop()
  }

  render () {
    return (
      <div>
        <div aria-hidden
          id='top-arrow'
          className={`top-arrow ${this.state.atTopOfPage ? 'button-hide' : ''}`}
          onClick={this.scrollToTop}
          role='button' />
      </div>
    )
  }
}

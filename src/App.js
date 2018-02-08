import React, { Component } from 'react'
import data from './promotionJson.json' //import file Json
import BackToTopButton from './BackToTopButton'

class App extends Component {
  componentDidMount() {
    console.log(data)
    this.setState({
      phone: data || [], //เอาData ใส่ state ที่สร้างขึ้น
    })
  }

  state = {
    phone: [], //สร้าง state เป็น array 
  } 

  render() { 
    return (
      <div className="center-format">
          <BackToTopButton/>
          <table class="table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Phone Number</th>
                <th>Promotion Code</th>
                <th>Amount</th>
              </tr>
            </thead>
            <tbody>
         
              {this.state.phone.map((val , index) => (
                <tr key={index}>

                   <td>{val.date}</td>
                   <td>{val.startTime}</td>
                   <td>{val.endTime}</td>
                   <td>{val.phoneNumber}</td> 
                   <td>{val.promotionId}</td>
                   <td>{val.amount} Bath</td>
                </tr>
              ))}
            </tbody>
         </table> 
      </div>
    );
  }
}

export default App;

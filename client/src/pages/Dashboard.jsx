import React, { useState } from 'react';

const Dashboard = ({ user }) => {
  const [valUser, setValUser] = useState('');
  const [valTag, setValTag] = useState('');
  const [number, setNumber] = useState('');
  const [accPartnerNumber, setAccPartnerNumber] = useState('');
  const [isEditable, setIsEditable] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: implement form submission logic
  };

  const handleEditToggle = () => {
    setIsEditable((prev) => !prev);
  };

  return (
    <div className="dashboard">
      <h2>Welcome, {user}!</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Valorant Username:
            <input
              type="text"
              value={valUser}
              onChange={(e) => setValUser(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Tag (e.g. #1234):
            <input
              type="text"
              value={valTag}
              onChange={(e) => setValTag(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Phone Number:
            <input
              type="tel"
              value={number}
              onChange={(e) => setNumber(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Accountability Partner's Phone Number:
            <input
              type="tel"
              value={accPartnerNumber}
              onChange={(e) => setAccPartnerNumber(e.target.value)}
              disabled={!isEditable}
              required
            />
          </label>
        </div>
        <button type="submit" disabled={!isEditable}>Submit</button>
      </form>
      <button onClick={handleEditToggle}>
        {isEditable ? 'Stop Editing' : 'Edit'}
      </button>
    </div>
  );
};

export default Dashboard;

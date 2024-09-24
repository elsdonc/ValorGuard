import React, { useState } from 'react';

const Dashboard = ({ user }) => {
  const [valorantUsername, setValorantUsername] = useState('');
  const [tag, setTag] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [partnerPhoneNumber, setPartnerPhoneNumber] = useState('');
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
              value={valorantUsername}
              onChange={(e) => setValorantUsername(e.target.value)}
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
              value={tag}
              onChange={(e) => setTag(e.target.value)}
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
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
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
              value={partnerPhoneNumber}
              onChange={(e) => setPartnerPhoneNumber(e.target.value)}
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

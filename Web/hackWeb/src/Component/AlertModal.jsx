import React, { useRef, useState } from "react";
import './AlertModal.css'
import ReactModal from "react-modal";

ReactModal.setAppElement("#root");

function AlertModal({ show, onClose, alert }) {
  const videoRef = useRef(null); // Reference to the video element
  const [isPlaying, setIsPlaying] = useState(false); // Track play/pause state
  const [volume, setVolume] = useState(1);
  const [mapToggle, setMapToggle] = useState(false)
  const handleMapToggleChange = () => {
    setMapToggle(prevState => !prevState)
  }

  const handlePlayPause = () => {
    if (isPlaying) {
      videoRef.current.pause(); // Pause the video
    } else {
      videoRef.current.play(); // Play the video
    }
    setIsPlaying(!isPlaying);
  };

  const handleVolumeChange = (e) => {
    const newVolume = e.target.value;
    videoRef.current.volume = newVolume; // Update video volume
    setVolume(newVolume);
  };

  const handleReplay = () => {
    videoRef.current.currentTime = 0; // Restart the video
    videoRef.current.play(); // Play the video from the beginning
    setIsPlaying(true);
  };

  return (

    <ReactModal
      isOpen={show}
      onRequestClose={onClose}
      contentLabel="Alert Detail"
      className="modal-content"
      overlayClassName="modal-overlay"
    >
        <div className="alert-modal-wrapper">
        {
            alert && (
                <div className="alert-modal">
                    <div className="alert-details-container">
                        <div className="alert-details">
                            <h3>Sender Info</h3>
                            
                            <div className="location">
                                <button onClick={handleMapToggleChange}>See Location</button>
                            </div>
                        </div>
                    </div>
                    <div className="video-player-container">
                        <video
                            ref={videoRef}
                            className="video-player"
                            controls={false} // Custom controls
                        >
                            <source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4" />
                            Your browser does not support the video tag.
                        </video>

                        {/* Controls */}
                        <div className="video-controls">
                            <button onClick={handlePlayPause} className="video-button">
                                {isPlaying ? "Pause" : "Play"}
                            </button>
                            <button onClick={handleReplay} className="video-button">
                                Replay
                            </button>

                            <label className="volume-label">Volume:</label>
                            <input
                                type="range"
                                className="volume-slider"
                                min="0"
                                max="1"
                                step="0.1"
                                value={volume}
                                onChange={handleVolumeChange}
                            />
                        </div>
                    </div>
                    
                </div>
            )
        }
        
    </div>

    </ReactModal>
    
    
  )
}

export default AlertModal
import React, { useRef, useState } from "react";
import './AlertModal.css'

function AlertModal() {
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

  return (
    <div className="alert-modal-wrapper">
        <div className="alert-modal">
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
            <div className="location">
                <button onClick={handleMapToggleChange}>See Location</button>
            </div>
        </div>
    </div>
  )
}

export default AlertModal
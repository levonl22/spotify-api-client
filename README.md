## Setup

This project requires Spotify API credentials. Each user must create their own account and app.

### 1. Get Spotify Credentials

1. Go to the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/).
2. Log in and create a new app.
3. Copy your **Client ID** and **Client Secret**.

### 2. Set Environment Variables

**Option A: Eclipse (recommended for development)**

1. Right-click the project → Run As → Run Configurations…
2. Select your main class → Environment tab → New…
3. Add:

| Name                  | Value                  |
|-----------------------|-----------------------|
| SPOTIFY_CLIENT_ID      | *your client ID*       |
| SPOTIFY_CLIENT_SECRET  | *your client secret*   |

4. Apply and Run.

**Option B: System Environment Variables**

- **Windows CMD:**
```cmd
set SPOTIFY_CLIENT_ID=your_client_id
set SPOTIFY_CLIENT_SECRET=your_client_secret
java -jar spotify-cli.jar

Tetris Mechanics
===

Inputs
===

- Up: flips pieces
X Down: Accelerated Drop
X Space: Instant Drop
X Left, Right: ...

Grid
===

X Fixed Grid of (W,H)
X Complete a row it disappears
X ^^ Remaining rows move down to replace it

Blocks
===

X Block has fixed starting position
- Blocks flip/rotate in the same direction in a predictable manner
X Blocks have a fixed starting pose (don't do colour as we are recreating the original)
X Blocks can't move outside of the grid
X Order of the blocks is randomised
- ^^ But there is a next block indicator (UI)
- Rotation gives gradual pause in decent
- Allows you to do some rotation that don't seem possible (block shifts)
- Blocks stop when they hit other blocks
- ^^ But there is a slight pause allowing you to still move or rotate them
- ^^ Pressing down locks them
- ^^ They also lock after a set time
- ^^ You get a new block everytime your current block locks

Score
===

- You loose when the next block is stuck and cannot progress past it's starting position
- Level Indicator (UI)
- Score/Points Indicator (UI)
- Level Block Speed
- Level Points Multiplier
- Complete a row you get points added to your total
- Clearing more rows at once get's your more points (Rows Cleared Points Multiplier)
- When you reach a certain amount of points you go to the next level
- Leaderboard
- ^^ Rank, Name, Date, Score (UI)

Sound
===

Put in a sound to emulate the keypress sound heard in the video :)

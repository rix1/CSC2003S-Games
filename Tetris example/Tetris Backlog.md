TETRIS DEVELOPMENT
====================

=====WISHLIST=====

- Piece identifiers
	- square dont rotate
- Piece moves out from wall to rotate
- Refactor to skip casting of getX and getY


=====FINISHED=====

X Down: Accelerated Drop
X Space: Instant Drop
X Left, Right: ...
X Fixed Grid of (W,H)
X Complete a row it disappears
X ^^ Remaining rows move down to replace it
X Block has fixed starting position
X Blocks have a fixed starting pose (don't do colour as we are recreating the original)
X Blocks can't move outside of the grid
X Order of the blocks is randomised
X ^^ You get a new block everytime your current block locks
X Up: flips pieces
X Blocks flip/rotate in the same direction in a predictable manner
X You loose when the next block is stuck and cannot progress past it's starting position
X Allows you to do some rotation that don't seem possible (block shifts)
X Blocks stop when they hit other blocks
X ^^ Pressing down locks them
X ^^ They also lock after a set time
X Level Block Speed
X ^^ But there is a next block indicator (UI)

=====WORKING=====

- Level Indicator (UI)

- Score/Points Indicator (UI)
- Level Points Multiplier
- Complete a row you get points added to your total
- Clearing more rows at once get's your more points (Rows Cleared Points Multiplier)

- When you reach a certain amount of points you go to the next level


=====BACKLOG=====

- ^^ But there is a slight pause allowing you to still move or rotate them
- Rotation gives gradual pause in decent

- Leaderboard
- ^^ Rank, Name, Date, Score (UI)

- Put in a sound to emulate the keypress sound heard in the video
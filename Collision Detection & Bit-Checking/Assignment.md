## Collision Detection & Bit-Checking

The basic game which you have been given has basic and inefficient bounding-box collision detection system which is O(N2). The basic collision detection system uses bounding-box tests to perform, these are very inaccurate for non-rectangular sprites, and you will need to implement a pixel-checking function to perform accurate testing. This is the bulk of the assignment and will earn you up to 65% of the marks.

To get the further 35% you will need to improve the efficiency of the collision detection system. This can be done by implementing an entirely new collision detection system or through clever use of data structures to eliminate unnecessary checks. You will receive higher marks for implementing better systems.

There is a bonus 15% in this practical for improving the bounding-box test with a more suitable bounding-shape test. This should only be attempted after the above two sections have been completed.
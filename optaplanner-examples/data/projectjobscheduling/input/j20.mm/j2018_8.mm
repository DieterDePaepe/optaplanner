************************************************************************
file with basedata            : md338_.bas
initial value random generator: 10735818
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  175
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       29        4       29
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          1           5
   3        3          3           8  10  17
   4        3          3           6   9  11
   5        3          3          12  15  17
   6        3          1           7
   7        3          2           8  12
   8        3          2          13  16
   9        3          2          12  14
  10        3          3          11  16  18
  11        3          2          14  21
  12        3          2          16  18
  13        3          2          18  19
  14        3          1          15
  15        3          2          19  20
  16        3          1          21
  17        3          2          19  20
  18        3          2          20  21
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     1       0    6    0    8
         2     6       0    4    6    0
         3     9       0    3    4    0
  3      1     7       5    0    1    0
         2     8       4    0    0   10
         3     9       0    6    0    6
  4      1     4       0    4    0    7
         2     7       7    0    0    6
         3     9       0    3    0    6
  5      1     1       2    0    6    0
         2     5       1    0    0    1
         3     9       0    9    6    0
  6      1     5       0    4    6    0
         2     8       0    3    5    0
         3     9       6    0    0    6
  7      1     4       0    8    8    0
         2     7       5    0    0    8
         3    10       0    7    0    2
  8      1     4       9    0    4    0
         2     5       8    0    3    0
         3     6       0    4    0    7
  9      1     4       0    4    0    5
         2     6       9    0    6    0
         3    10       0    2    6    0
 10      1     1       0    4   10    0
         2     5       0    4    0    6
         3     6       8    0    0    2
 11      1     2       0    5    0    5
         2     5       0    1    9    0
         3     9       8    0    6    0
 12      1     5       0    5    0   10
         2     6       0    3    0    9
         3     8       9    0    0    9
 13      1     1       5    0    0    5
         2     3       0    4    5    0
         3     9       0    4    3    0
 14      1     1       0    4    3    0
         2     6       4    0    3    0
         3    10       4    0    0    8
 15      1     6       7    0    6    0
         2    10       0    3    6    0
         3    10       3    0    6    0
 16      1     2       6    0    0    2
         2     6       0    8    7    0
         3     8       3    0    1    0
 17      1     5       0    3    0    2
         2     7       0    2    2    0
         3     9       9    0    1    0
 18      1     2       0    3    0    9
         2     8      10    0    2    0
         3     8       8    0    0    5
 19      1     6       0    8    9    0
         2     6       0    3    0    5
         3     9       1    0    9    0
 20      1     9       4    0    0    5
         2     9       7    0    5    0
         3     9       0    2    5    0
 21      1     2       6    0    0    9
         2     6       4    0    0    8
         3     9       3    0    0    3
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   18   13   73   93
************************************************************************
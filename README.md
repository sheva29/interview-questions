# interview-questions

The purpose of this repository is twofold:

  1. To document questions I am asked and to provide for my own erudition a
      more complete solution than I am able to provide under the time constraints of
      an interview.
  2. To provide a bank of questions that I have to ask potential candidates when I
      am on the other end of the interview.

For both cases, a fully worked through set of solutions is more valuable for my
understanding and for my ability to parse a candidate's approach.

## The questions.

I'll list here the top level description of the questions, and link to the
relevant code samples appropriately.


### Lowest Common Ancestor

This problem is a pretty good data structures and algorithms question, touching
on binary search trees at the least and probably stacks for most candidates. It
also is a great algorithm question because there are a few pieces of iteration
that we can go through on how to improve the algorithm.

#### Problem statement

Given a binary search tree of size `n` and a list of `k` targets, find their
lowest common ancestor. For example, in the binary search tree

```
      4
     / \
    2   5
   / \
  1   3
```

with the targets `[1, 3]`, the lowest common ancestor is 2.

#### Key components:
- Understanding how a binary search tree works, implementing a node class
- Understanding the stack based approach
- Using min/max instead of the whole list of targets
- Using top down recursion instead of a stack
- Understanding order notation of the algorithm, however they implement it.
- If they use min/max or top-down recursion, calling out the possibility of the
  target not existing in the tree, and the runtime complexity implications of
  validating its existence.

### Disjoint Ranges:

This problem doesn't touch much on data structures, but it is a pretty good
algorithms question. At best, it requires an understanding of binary search, as
well as forcing the candidate to think about the conditions for search.

#### Problem statement:

Given a list of sorted, increasing, disjoint ranges, and a new range, add the new
range of integers into the list, merging any to maintain the properties of
sorted, increasing, and disjoint.

For example, given the list of ranges
```
     [(-3, 5), (7, 8), (200, 500), (1000, 1001)]
```

and the new range `(4, 7)`, the output should be
```
    [(-3, 8), (200, 500), (1000, 1001)]
```

#### Key Components:

- Determining where to place the node using comparisons of the ranges
- using binary search to do so.
- correctly removing the indices to be merged as well as not removing indices
  that are out of scope.

### Hungry Rabbit

This is a pretty interesting problem, but it takes a bit longer than most
interviews would allow. It is not a difficult algorithm, but it is a great
question for determining code hygiene, since there is a lot of opportunity for
code deduplication and that sort of thing.

#### Problem Statement:

A very hungry rabbit is placed in the center of of a garden, represented by a
rectangular N x M 2D matrix.

The values of the matrix will represent numbers of carrots available to the
rabbit in each square of the garden. If the garden does not have an exact
center, the rabbit should start in the square closest to the center with the
highest carrot count.

On a given turn, the rabbit will eat the carrots available on the square that it
is on, and then move up, down, left, or right, choosing the the square that has
the most carrots. If there are no carrots left on any of the adjacent squares,
the rabbit will go to sleep. You may assume that the rabbit will never have to
choose between two squares with the same number of carrots.

Write a function which takes a garden matrix and returns the number
of carrots the rabbit eats eg.

```
    [[5, 7, 8, 6, 3],
     [0, 0, 7, 0, 4],
     [4, 6, 3, 4, 9],
     [3, 1, 0, 5, 8]]
```

Should return `27`


#### Key components:

- Deduplicates code for enumerating possible moves for both starting and next moves.
- is able to parse the matrix
- has an intuitive approach for dealing with the problem.

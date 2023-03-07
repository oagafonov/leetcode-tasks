# n != 1 * 2 * 3 * .. * n-1 * n
def factorial(n: int) -> int:
    if n == 1:
        return 1
    else:
        return n * factorial(n - 1)


if __name__ == "__main__":
    print(factorial(10))

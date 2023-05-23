def prime(n):
    prime_arr = []
    prime = [0]*(n+1)
    for i in range(2, n):
        for j in range(i+i, n, i):
            if prime[j] == 0:
                prime[j] = j
            else:
                continue

    for i in range(2, n+1):
        if prime[i] == 0:
            prime_arr.append(i)

    return prime_arr


prime_arr = prime(1000001)


def sol(n, start, end):
    global prime_arr
    ans = 0
    for i in range(len(prime_arr)):
        if start <= prime_arr[i] <= end and str(n) in str(prime_arr[i]):
            ans += 1

    return ans


for tc in range(int(input())):
    include, start, end = map(int, input().split())
    print(f'#{tc+1} {sol(include, start, end)}')

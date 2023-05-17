primeArr = []
def calPrime(n):
    prime = [0]*1001
    retArr = []
    for i in range(2, n):
        for j in range(i+i, n, i):
            if prime[j] != 0:
                continue
            prime[j] = j

    for i in range(2, len(prime)):
        if prime[i] == 0:
            retArr.append(i)

    return retArr


def sol(n):

    ans = 0
    length = len(primeArr)
    for i in range(length):
        for j in range(i, length):
            for k in range(j, length):
                sum_val = primeArr[i]+primeArr[j]+primeArr[k]
                if sum_val == n:
                    ans += 1
                elif sum_val > n:
                    break

    return ans


primeArr = calPrime(1001)


for tc in range(int(input())):
    n = int(input())
    res = sol(n)
    print(f'#{tc+1} {res}')
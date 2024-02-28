# playground

## 목표

spring data JPA 에서 지원하는 [FetchType.EAGER, FetchType.LAZY] 에 대한 이해

## 구현

즉시로딩(EAGER)과 지연로딩(LAZY)을 사용하는 엔티티를 생성한 뒤 로그로 jpa query를 출력하여 어느 시점에서 MainEntity를 호출하는지 확인.

## 결과

* 즉시로딩 (EAGER)
  * 서브 엔티티가 호출되는 모든 과정에서 메인엔티티를 호출함
* 지연로딩 (LAZY)
  * 서브 엔티티가 호출되고 나서 메인 엔티티를 사용할 때 메인엔티티를 호출함

## API
|     API명     |        엔트포인트         | http method |    param     |                     requestBody                      |        비고         |
|:------------:|:--------------------:|:-----------:|:------------:|:----------------------------------------------------:|:-----------------:|
|  메인 엔티티 저장   |        /main         |    POST     |     none     |                   {"name": string}                   |                   |
| 메인 엔티티 전체 조회 |        /main         |     GET     |     none     |                         none                         |                   |
| 메인 엔티티 개별 조회 |     /main/{int}      |     GET     |     none     |                         none                         |                   |
|  서브 엔티티 저장   |         /sub         |    POST     |     none     | {"name": string, <br>"seq": int, <br>"flag": string} |                   |
| 서브 엔티티 전체 조회 |         /sub         |     GET     | flag: string |                         none                         | flag값: {'e', 'l'} |
| 서브 엔티티 개별 조회 |      /sub/{int}      |     GET     | flag: string |                         none                         | flag값: {'e', 'l'} |
| 지연 로딩 시점 확인  | /sub/lazy/test/{int} |     GET     | flag: string |                         none                         | flag값: {'t', 'f'} |
